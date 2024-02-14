package com.Andersoon_kotlin.mariobrosnumero

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.Andersoon_kotlin.mariobrosnumero.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaNumeros: MutableList<Int> = mutableListOf()
    private var progresso = 0

    private val listaImgs: MutableList<Int> = mutableListOf(
        R.drawable.n0, R.drawable.n1, R.drawable.n2, R.drawable.n3,
        R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7,
        R.drawable.n8, R.drawable.n9, R.drawable.n10,R.drawable.caixa
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Esconder o StatusBar

        binding.numeroSurpresa.setBackgroundResource(R.drawable.caixa) //vai aparecer a caixa surpresa

        binding.btPlay.setOnClickListener {view ->
            val numeroDigitado = binding.editNumero.text.toString()

            if (numeroDigitado.isEmpty()){
                mensagem(view,"Coloque um numero","#FF0000")
            }else{
                gerarNumeroAleatorio(view,numeroDigitado.toInt())
            }
        }

        binding.btReset.setOnClickListener{
            binding.editNumero.setText("")
            progresso = 0
            binding.linearProgressIndicator.setProgress(progresso,true)
        }


    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun gerarNumeroAleatorio(view: View, numeroDigitado: Int){

        for (n in 0..11) {
            listaNumeros.add(n)
        }

        val numeroAleatorio = java.util.Random().nextInt(11 )

        val imgNumero = when (numeroAleatorio) {
            0 -> {
                listaImgs[0]
            }

            1 -> {
                listaImgs[1]
            }

            2 -> {
                listaImgs[2]
            }

            3 -> {
                listaImgs[3]
            }

            4 -> {
                listaImgs[4]
            }

            5 -> {
                listaImgs[5]
            }

            6 -> {
                listaImgs[6]
            }

            7 -> {
                listaImgs[7]
            }

            8 -> {
                listaImgs[8]
            }

            9 -> {
                listaImgs[9]
            }

            10 -> {
                listaImgs[10]
            }

            else -> {
                listaImgs[11]
            }
        }
        if (numeroDigitado != numeroAleatorio) {
            binding.numeroSurpresa.setBackgroundResource(R.drawable.caixa)
            mensagem(view,"Você errou! tente novamente!","#FF0000")
            progresso += 30
            binding.linearProgressIndicator.setProgress(progresso, true)
        } else {
            mensagem(view,"Parabens você acertou!","#2D9031")
            progresso -= 120
            binding.numeroSurpresa.setBackgroundResource(imgNumero)
            binding.editNumero.setText("")
            binding.linearProgressIndicator.setProgress(progresso, true)
            progresso = 0
        }

        if (progresso > 90){
        //navegar pra tela de Game Over
            val intent = Intent(this,GameOver::class.java)
            startActivity(intent)
        }
    }


    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}














