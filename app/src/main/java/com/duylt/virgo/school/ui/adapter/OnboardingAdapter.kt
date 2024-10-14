package com.duylt.virgo.school.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.duylt.virgo.school.R
import com.duylt.virgo.school.databinding.ItemOnBoardingBinding
import com.duylt.virgo.school.model.OnboardingModel
import com.duylt.virgo.school.ui.base.BaseRecyclerView

class OnboardingAdapter(
    private val contextParams: Context
) : BaseRecyclerView<OnboardingModel>() {
    override fun getItemLayout(): Int = R.layout.item_on_boarding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun setData(binding: ViewDataBinding, item: OnboardingModel, layoutPosition: Int) {
        if (binding is ItemOnBoardingBinding) {
            binding.tvTitle.text = contextParams.getString(item.titleResource)
            binding.tvDescription.text = contextParams.getString(item.descriptionResource)
            Glide.with(contextParams).load(item.rawRes).into(binding.imgGuide)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<OnboardingModel>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}