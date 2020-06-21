package th.ac.psu.commsci.multichoicealertdialog



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button
        val mShowAlertDialogBtn = findViewById<Button>(R.id.showAlertDialogBtn)
        //textView
        val mTxtView = findViewById<TextView>(R.id.txtView)

        //button click to show alertdialog
        mShowAlertDialogBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            // String array for alert dialog multi choice items
            val colorsArray = arrayOf("Coffee", "Ovantin", "Green", "Yellow", "White", "Purple")
            // Boolean array for initial selected items
            val checkedColorsArray = booleanArrayOf(
                true, // Cofee checked
                false, // Ovantin
                false, // Green
                true, // Yellow checked
                false, // White
                false  //Purple
            )
            // Convert the color array to list
            val colorsList = Arrays.asList(*colorsArray)
            //setTitle
            builder.setTitle("Select colors")
            //make alert dialog multi choice
            builder.setMultiChoiceItems(
                colorsArray,
                checkedColorsArray
            ) { dialog, which, isChecked ->
                // Update the current focused item's checked status
                checkedColorsArray[which] = isChecked
                // Get the current focused item
                val currentItem = colorsList[which]
                // Notify the current action
               Toast.makeText(applicationContext, currentItem + " " + isChecked, Toast.LENGTH_SHORT
                ).show()
            }
            // Set the positive/yes button click listener
            builder.setPositiveButton("OK") {dialog  , which ->
                // Do something when click positive button
                mTxtView.text = "Your preferred colors..... \n"
                for (i in checkedColorsArray.indices) {
                    val checked = checkedColorsArray[i]
                    if (checked) {
                        mTxtView.text = mTxtView.text.toString() + colorsList[i] + "\n"
                    }
                }
            }
            // Set the neutral/cancel button click listener
            builder.setNeutralButton("Cancel") { dialog, which ->
                // Do something when click the neutral button
            }
            val dialog = builder.create()
            // Display the alert dialog on interface
            dialog.show()
        }

    }
}