package com.example.netflixclone

import android.accounts.AuthenticatorException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FormLogin : AppCompatActivity() {
    private lateinit var binding : ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        verificaUsuarioLogado()

        binding.textTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha  = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensgemErro
            if(email.isEmpty() || senha.isEmpty()) {
                mensagem_erro.setText("Preencha todos os campos!")
            }else {
                mensagem_erro.setText("")
                autenticarUsuario()
            }
        }

    }

    private fun autenticarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha  = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensgemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                redirecionaParaHome()
            }
        }.addOnFailureListener {
            var erro = it

            when {
                erro is FirebaseAuthInvalidCredentialsException -> mensagem_erro.setText("Email ou senha incorreto, verifique suas credenciais")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet")
                else -> mensagem_erro.setText("Erro ao fazer login")
            }
        }
    }

    private fun verificaUsuarioLogado() {
        val usuario = FirebaseAuth.getInstance().currentUser

        if(usuario != null) {
            redirecionaParaHome()
        }
    }

    private fun redirecionaParaHome() {
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}