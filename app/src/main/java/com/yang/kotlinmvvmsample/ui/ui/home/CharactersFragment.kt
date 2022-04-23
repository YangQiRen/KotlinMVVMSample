package com.yang.kotlinmvvmsample.ui.ui.home

import androidx.fragment.app.viewModels
import com.yang.baselibs.base.BaseFragment
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.databinding.FragmentCharactersBinding
import com.yang.kotlinmvvmsample.databinding.ItemCharacterBinding
import com.yang.kotlinmvvmsample.ui.adapter.CharacterAdapter
import kotlinx.coroutines.flow.collectLatest

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    lateinit var characterAdapter: CharacterAdapter

    override val layoutId: Int
        get() = R.layout.fragment_characters

    override fun getVM(): CharactersViewModel = charactersViewModel

    override fun bindVM(binding: FragmentCharactersBinding, viewModel: CharactersViewModel) {
        with(binding) {
            characterAdapter = CharacterAdapter { itemCharacterBinding: ItemCharacterBinding, character: Character -> }
            rvCharacters.adapter = characterAdapter
        }

        launchOnLifecycleScope {
            viewModel.charactersFlow.collectLatest { characterAdapter.submitData(it) }
        }
    }
}