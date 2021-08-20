package com.mx.ddrtoolsexample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mx.ddrtools.components.dialogs.DDRLoader
import com.mx.ddrtools.components.dialogs.DDRLottieLoader
import com.mx.ddrtools.components.item_picker.DDRPicker
import com.mx.ddrtools.components.snackbar.DDRSnackBar
import com.mx.ddrtools.model.DDRItemPicker
import com.mx.ddrtoolsexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var rootView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootView = ActivityMainBinding.inflate(layoutInflater)

        setContentView(rootView.root)


        rootView.supplyParams.setTitle("Project")
        rootView.supplyParams.setSubtitle("THOMASAN")

        rootView.btnTest.setOnClickListener {

            /*DDRSnackBar.make(
                view = rootView.root,
                message = R.string.app_name,
                icon = R.drawable.image,
                background = R.drawable.common_snackbar_background,
                messageStyle = R.style.snackBarStyle,
                status = true
            ){ // onClick action
                it.dismiss()
            }.show()*/

            /*if (rootView.rotate.isStarted()){
                rootView.rotate.stop()
            }else{
                rootView.rotate.start()
            }*/


            /*var loaderDialogFragment = DDRLoader(R.color.teal_200)
            loaderDialogFragment.isCancelable = false
            loaderDialogFragment.show(this.supportFragmentManager, "PROGRESS_TAG")*/


            /*var elements = arrayListOf(
                DDRItemPicker(id = "1", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "2", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "3", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "4", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "5", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "6", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "7", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "8", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "9", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "10", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "11", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "12", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "13", name = "FOLIO 123456", selected = false),
                DDRItemPicker(id = "14", name = "FOLIO 123456", selected = false)
            )

            DDRPicker(
                items = elements,
                multiChoice = true,
                title = "Título",
                acceptButtonStyle = R.style.buttonAcceptTheme,
                cancelButtonStyle = R.style.buttonCancelTheme,
                titleStyle = R.style.DDRPicker_Title_EX,
                cancelable = false
            ) {

            }.show(supportFragmentManager,"TG")*/

            var loaderDialogFragment = DDRLottieLoader("splash_animation.json")
            loaderDialogFragment.isCancelable = false
            loaderDialogFragment.show(this.supportFragmentManager, "PROGRESS_TAG")
        }


    }

}