package ru.kleverkzn.shopinglist.presentation

import androidx.lifecycle.ViewModel
import ru.kleverkzn.shopinglist.data.ShopListRepositoryImpl
import ru.kleverkzn.shopinglist.domain.AddShopItemUseCase
import ru.kleverkzn.shopinglist.domain.EditShopItemUseCase
import ru.kleverkzn.shopinglist.domain.GetShopItemUseCase
import ru.kleverkzn.shopinglist.domain.ShopItem

class ShopItemViewModel: ViewModel() {


    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO: shop error input name
            result = false
        }
        if (count <= 0) {
            // TODO: shop error input count
            result = false
        }
        return result
    }
}