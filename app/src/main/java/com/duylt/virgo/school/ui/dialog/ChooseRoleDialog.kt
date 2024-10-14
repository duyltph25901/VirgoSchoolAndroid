package com.duylt.virgo.school.ui.dialog

import android.content.Context
import com.duylt.virgo.school.R
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.DialogChooseRoleBinding
import com.duylt.virgo.school.ui.base.BaseDialog

class ChooseRoleDialog(
    context: Context,
    private val onRoleTeacherSelected: () -> Unit,
    private val onRoleStudentSelected: () -> Unit
) : BaseDialog<DialogChooseRoleBinding>(context) {

    override fun getLayoutDialog(): Int = R.layout.dialog_choose_role

    override fun onClickViews() {
        mBinding.apply {
            buttonStudent.click {
                onRoleStudentSelected.invoke()
                cancel()
            }

            buttonTeacher.click {
                onRoleTeacherSelected.invoke()
                cancel()
            }
        }
    }

}