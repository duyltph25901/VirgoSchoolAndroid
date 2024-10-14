package com.duylt.virgo.school.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.duylt.virgo.school.R
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.ItemLanguageBinding
import com.duylt.virgo.school.model.LanguageModel
import com.duylt.virgo.school.ui.base.BaseRecyclerView

class LanguageAdapter(
    private val contextParams: Context,
    private val onLanguageSelected: (language: LanguageModel, index: Int) -> Unit
) : BaseRecyclerView<LanguageModel>() {

    var indexSelected: Int = -1
        set(value) {
            field = value
            notifyItemChanged(value)
        }

    override fun getItemLayout(): Int = R.layout.item_language

    override fun setData(binding: ViewDataBinding, item: LanguageModel, layoutPosition: Int) {
        if (binding is ItemLanguageBinding) {
            Glide.with(contextParams).load(item.image).into(binding.imgCountry)
            binding.txtLanguage.text = item.languageName
            binding.imgChecked.isActivated = indexSelected == layoutPosition
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<LanguageModel>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onClickViews(binding: ViewDataBinding, obj: LanguageModel, layoutPosition: Int) {
        if (binding is ItemLanguageBinding) {
            binding.root.click {
                onLanguageSelected.invoke(obj, layoutPosition)
            }
        }
    }
}