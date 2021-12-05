package com.yang.baselibs.base

interface IView {

    /**
     * 顯示載入中
     */
    fun showLoading()

    /**
     * 隱藏載入中
     */
    fun hideLoading()

    /**
     * 使用默認樣式顯示訊息: CustomToast
     */
    fun showDefaultMsg(msg: String)

    /**
     * 顯示一般訊息
     */
    fun showMsg(msg: String)

    /**
     * 顯示錯誤訊息
     */
    fun showError(errorMsg: String)

}