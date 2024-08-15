package com.bassem.appstore

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bassem.appstore.databinding.ActivityMainBinding
import com.bassem.appstore.utilities.AppConstants.REQUEST_CODE_NOTIFICATION_PERMISSION
import com.bassem.appstore.utilities.createNotificationChannel
import com.bassem.appstore.utilities.hasNotificationPermission
import com.bassem.appstore.utilities.scheduleHourlyWork
import com.bassem.appstore.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.security.Permission

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (hasNotificationPermission()) {
            Log.i("Notification","app is having permission")
            scheduleHourlyWork()
            createNotificationChannel()
        } else {
            requestNotificationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_NOTIFICATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scheduleHourlyWork()
                createNotificationChannel()
            } else {
                Toast.makeText(
                    this,
                    "We cant send notification without allowing it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasNotificationPermission()) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_NOTIFICATION_PERMISSION
                )
            }
        }
    }
}