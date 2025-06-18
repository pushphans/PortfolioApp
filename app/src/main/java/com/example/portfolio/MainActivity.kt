package com.example.portfolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.portfolio.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var drawerLayout: DrawerLayout


    lateinit var toolbarTitle: TextView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        toolbarTitle = binding.toolbarTitle
        toolbar = binding.toolbar

        drawerLayout = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)

        onBackPressedDispatcher.addCallback(this) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                val navController = findNavController(R.id.fragmentContainerView)
                navController.popBackStack()
                if(!navController.popBackStack()){
                    finish()
                }
            }
        }

        binding.drawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                (R.id.experience) -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.experienceFragment)
                    drawerLayout.close()
                }

                (R.id.portfolio) -> {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://github.com/pushphans")
                    }
                    val chooser = Intent.createChooser(intent, "Open with")
                    startActivity(chooser)
                    drawerLayout.close()
                }

                (R.id.education) -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.educationFragment)
                    drawerLayout.close()
                }


                (R.id.share) -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
                    shareResume()
                    drawerLayout.close()
                }

                (R.id.contact) -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.contactFragment)
                    drawerLayout.close()
                }
            }
            true
        }


    }

    private fun shareResume(){
        val fileName = "Pushp_Resume.pdf"

        val inputStream = resources.openRawResource(R.raw.myresume)
        val outFile = File(cacheDir, fileName)

        inputStream.use { input ->
            FileOutputStream(outFile).use{output ->
                input.copyTo(output)
            }
        }

        val uri = FileProvider.getUriForFile(this, "${packageName}.fileprovider",outFile)

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Pushp Hans - Resume")
            putExtra(Intent.EXTRA_TEXT,"You can use my resume to refer me😅")
        }

        startActivity(Intent.createChooser(intent, "Share via"))
    }

}