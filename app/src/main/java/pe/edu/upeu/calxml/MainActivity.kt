package pe.edu.upeu.calxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var valAnt = 0.0
    private var valAct = 0.0
    private var operador = ""
    private lateinit var resultado: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultado = findViewById(R.id.txtResult)  // Asegúrate de que el id es correcto
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonSetup()
    }

    private fun buttonSetup() {
        val listaB = arrayOf(R.id.btn9, R.id.btn8, R.id.btn7, R.id.btn5, R.id.btn4, R.id.btn6, R.id.btn3, R.id.btn2, R.id.btn1,
            R.id.btnsum, R.id.btnmult, R.id.btnrest, R.id.btnresult)
        for (button in listaB) {
            val butonx = findViewById<Button>(button)
            butonx.setOnClickListener { onClickListener(butonx) }
        }
    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.btn9, R.id.btn8, R.id.btn7, R.id.btn6, R.id.btn5, R.id.btn4,  R.id.btn3, R.id.btn2,  R.id.btn1, -> {
                val butonx = findViewById<Button>(view.id)
                appendNum(butonx.text.toString())
            }
            R.id.btnsum -> {
                prepareOperation("+")
            }
            R.id.btnrest -> {
                prepareOperation("-")
            }
            R.id.btnmult -> {
                prepareOperation("*")
            }
            R.id.btnresult -> {
                calculateResult()
            }

        }
    }

    private fun appendNum(valor: String) {
        resultado.append(valor)
    }

    private fun prepareOperation(op: String) {
        if (resultado.text.isNotEmpty()) {
            valAnt = resultado.text.toString().toDouble()
            operador = op
            resultado.text.clear()
        }
    }

    private fun calculateResult() {
        if (resultado.text.isNotEmpty()) {
            valAct = resultado.text.toString().toDouble()
            val result = when (operador) {
                "+" -> valAnt + valAct
                "-" -> valAnt - valAct
                "*" -> valAnt * valAct
                else -> 0.0
            }
            resultado.setText(result.toString())
            valAnt = result
            operador = ""
        }
    }

    private fun clear() {
        resultado.text.clear()
        valAnt = 0.0
        valAct = 0.0
        operador = ""
    }
}
