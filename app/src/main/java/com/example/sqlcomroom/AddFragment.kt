package com.example.sqlcomroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.sqlcomroom.databinding.FragmentAddBinding
import com.example.sqlcomroom.model.User

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val mainViewModel: ViewModel = MainViewModel(context)
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.lerTodosOsDados

        fun validar(nome: String, sobrenome: String, idade: String): Boolean {
            return !(nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty())
        }

        fun inserirNoBanco() {
            val nome = binding.editNome.text.toString()
            val sobrenome = binding.editSobreNome.text.toString()
            val idade = binding.editTextNumber.text.toString()

            if (validar(nome, sobrenome, idade)) {
                val user = User(0, nome, sobrenome, idade.toInt())
                mainViewModel.addUser(user)
                Toast.makeText(context, "Usuário Cadastrado", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listFragment_to_addFragment)
            } else {
                Toast.makeText(context, "Preencha os campos corretamente!", Toast.LENGTH_LONG)
                    .show()
            }

            // Inflate the layout for this fragment
            binding = FragmentAddBinding.inflate(
                layoutInflater, container, false
            )
        }
        return binding.root
    }
}
