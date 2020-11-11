package my.edu.tarc.insurans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Global Variable
    private var premium: Int = 0
    private var malePremium: Int = 0
    private var smokerPremium: Int = 0
    private var total: Int = 0

    private lateinit var textViewPremium: TextView
    private lateinit var textViewMalePremium: TextView
    private lateinit var textViewSmokerPremium: TextView
    private lateinit var textViewTotal: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO 1: Link UI to code
        val spinnerAge: Spinner = findViewById(R.id.spinnerAge)
        val radioButtonGender: RadioGroup = findViewById(R.id.radioGroupGender)
        val checkBoxSmoker: CheckBox = findViewById(R.id.checkBoxSmoker)

        textViewPremium = findViewById(R.id.textViewPremium)
        textViewMalePremium = findViewById(R.id.textViewMalePremium)
        textViewSmokerPremium = findViewById(R.id.textViewSmokerPremium)
        textViewTotal = findViewById(R.id.textViewTotal)

        //TODO 2: set event listener
        spinnerAge.onItemSelectedListener = this

        radioButtonGender.setOnCheckedChangeListener { it, _ ->
            val male: Int = it.checkedRadioButtonId

                malePremium = if(male == R.id.radioButtonMale){
                    val age: Int = spinnerAge.selectedItemPosition
                    when(age){
                        1 -> 50
                        2 -> 100
                        3 -> 150
                        4,5 -> 200
                        else -> 0
                    }
                }else
                    0



            textViewMalePremium.text = malePremium.toString()
        }//End of radioButton
        
        checkBoxSmoker.setOnCheckedChangeListener { compoundButton, _ ->
            if(compoundButton.isChecked){
                //TODO: calculate smoker premium
                val age: Int = spinnerAge.selectedItemPosition
                smokerPremium = when(age) {
                    1 -> 100
                    2 -> 150
                    3 -> 200
                    4 -> 250
                    5 -> 300
                    else -> 0
                }
            }else{
                smokerPremium = 0
            }
            textViewSmokerPremium.text = smokerPremium.toString()

        }// end of checkbox

        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        buttonCalculate.setOnClickListener{
            total = premium + malePremium + smokerPremium
            textViewPremium.text = premium.toString().format(Locale.getDefault())
            textViewMalePremium.text = malePremium.toString()
            textViewSmokerPremium.text = smokerPremium.toString()
            textViewTotal.text = total.toString().format(Locale.getDefault())
        }

        val buttonReset: Button = findViewById(R.id.buttonReset)
        buttonReset.setOnClickListener{
            spinnerAge.setSelection(0)//reset selection to the first item
            radioButtonGender.clearCheck()
            checkBoxSmoker.isChecked =  false
            textViewPremium.text = null
            textViewMalePremium.text = null
            textViewSmokerPremium.text = null
            textViewTotal.text = null
        }
    }
    /*
    fun calculatePremium(view: View){
        val radioGroupGender: RadioGroup =d findViewById(R.id.radioGroupGender)
        val gender = radioGroupGender.checkedRadioButtonId
        val age = spinnerAge.selectedItemPosition
        if(gender == R.id.radioButtonMale){
            //TODO: calculate male Premium
        }
    }*/


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
        //Toast.makeText(context:this, text"Item selected = ${index}", Toast.LENGTH_SHORT).show()
        when(index){
            0 -> premium = 60
            1 -> premium = 70
            2 -> premium = 90
            3 -> premium = 120
            4,5 -> premium = 150
            //TODO: complete the assignment of basic premium
        }
        textViewPremium.text = premium.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO: daf
    }

}