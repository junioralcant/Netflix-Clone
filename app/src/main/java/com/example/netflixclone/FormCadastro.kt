package com.example.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        toolBar()

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensgemErro

            if(email.isEmpty() || senha.isEmpty()){
                mensagem_erro.setText("Preencha todos os campos!")
            } else {
                mensagem_erro.setText("")
                cadastrarUsuario()
            }
        }

    }

    private fun toolBar() {
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))

    }

    private fun cadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensgemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
            }
        }.addOnFailureListener {
            var erro = it

            when {
                erro is FirebaseAuthWeakPasswordException -> mensagem_erro.setText("Informe uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> mensagem_erro.setText("Este email já foi cadastrado")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão a internet")
                else -> mensagem_erro.setText("Erro ao cadastrar usuário")
            }

        }
    }
}