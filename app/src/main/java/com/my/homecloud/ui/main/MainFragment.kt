package com.my.homecloud.ui.main

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.my.homecloud.databinding.FragmentMainBinding


/** The request code for requesting [Manifest.permission.READ_EXTERNAL_STORAGE] permission. */
private const val READ_EXTERNAL_STORAGE_REQUEST = 0x1045

/**
 * Code used with [IntentSender] to request user permission to delete an image with scoped storage.
 */
private const val DELETE_PERMISSION_REQUEST = 0x1033

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // TODO: Use the ViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.grantPermissionButton.setOnClickListener { requestPermission() }

        if (haveStoragePermission()) {
            binding.grantPermissionButton.visibility = View.GONE
            viewModel.loadImages()
        }

        return binding.root
//        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    /**
     * Convenience method to check if [Manifest.permission.READ_EXTERNAL_STORAGE] permission
     * has been granted to the app.
     */
    private fun haveStoragePermission() = ContextCompat.checkSelfPermission(
        mContext, Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    /**
     * Convenience method to request [Manifest.permission.READ_EXTERNAL_STORAGE] permission.
     */
    private fun requestPermission() {
        if (!haveStoragePermission()) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
//            ActivityCompat.requestPermissions(activity, permissions, READ_EXTERNAL_STORAGE_REQUEST)
            requestPermissionLauncher.launch(permissions)
        }
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val readExternalGranted = result[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
        val writeExternalGranted = result[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: false
        if (readExternalGranted != null && readExternalGranted) {
            // Precise location access granted.
            Log.i("TAG", "permission granted.")
            Toast.makeText(
                requireActivity().applicationContext, "permission granted.", Toast.LENGTH_LONG
            ).show()

            viewModel.loadImages()

        } else if (writeExternalGranted != null && writeExternalGranted) {
            //
            Toast.makeText(
                requireActivity().applicationContext,
                "Write permission granted.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            // No perm granted.
            Toast.makeText(
                requireActivity().applicationContext, "permission NOT granted.", Toast.LENGTH_LONG
            ).show()
        }

    }


    private fun deleteImage(image: MediaStoreImage) {
//        MaterialAlertDialogBuilder(this)
//            .setTitle(R.string.delete_dialog_title)
//            .setMessage(getString(R.string.delete_dialog_message, image.displayName))
//            .setPositiveButton(R.string.delete_dialog_positive) { _: DialogInterface, _: Int ->
//                viewModel.deleteImage(image)
//            }
//            .setNegativeButton(R.string.delete_dialog_negative) { dialog: DialogInterface, _: Int ->
//                dialog.dismiss()
//            }
//            .show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}