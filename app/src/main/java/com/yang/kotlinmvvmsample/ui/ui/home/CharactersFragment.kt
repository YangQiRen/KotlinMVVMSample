package com.yang.kotlinmvvmsample.ui.ui.home

import androidx.fragment.app.viewModels
import com.yang.baselibs.base.BaseFragment
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.FragmentCharactersBinding

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_characters

    override fun getVM(): CharactersViewModel = charactersViewModel

    override fun bindVM(binding: FragmentCharactersBinding, vm: CharactersViewModel) {

    }
}