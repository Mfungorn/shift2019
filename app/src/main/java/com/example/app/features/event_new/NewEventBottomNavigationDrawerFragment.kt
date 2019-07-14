package com.example.app.features.event_new

import android.annotation.TargetApi
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.example.app.R
import com.example.app.core.MvpBottomSheetDialogFragment
import com.example.app.core.model.Expense
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.dialog_add_expense.view.*
import kotlinx.android.synthetic.main.fragment_new_event_bottom_navigation_drawer.*
import kotlinx.android.synthetic.main.fragment_new_event_bottom_navigation_drawer.view.*

class NewEventBottomNavigationDrawerFragment(private var expenses: ArrayList<Expense>) : MvpBottomSheetDialogFragment(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationView: NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        navigationView = view.navigation_view
        navigationView.setNavigationItemSelectedListener(this)

        view.button_add_new_expense.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_expense, null)
            val alertDialog = AlertDialog.Builder(view.context).create()
            alertDialog.setTitle("Adding new expense")
            alertDialog.setCancelable(true)

            val nameEditText = dialogView.edit_add_expense_name
            val costEditText = dialogView.edit_add_expense_cost

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Add") { _: DialogInterface, _: Int ->
                addCarryToNavigationDrawer(
                    Expense(nameEditText.text.toString(), costEditText.text.toString().toDouble())
                )
                alertDialog.dismiss()
            }

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _: DialogInterface, _: Int ->
                alertDialog.dismiss()
            }

            alertDialog.setView(dialogView)
            alertDialog.show()
        }

        return view
    }

    private fun getLayoutID(): Int = R.layout.fragment_new_event_bottom_navigation_drawer

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        close_imageview.setOnClickListener {
            this.dismiss()
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun addCarryToNavigationDrawer(expense: Expense) {
        navigationView.menu.add("${expense.name}: ${expense.cost} ${getString(R.string.currency)}")
        expenses.add(expense)

        val total = expenses.stream()
            .mapToDouble { e -> e.cost }
            .sum()
        (parentFragment as NewEventFragment).setNewEventTotal(
            String.format("%.2f ${getString(R.string.currency)}", total))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
        // Bottom Navigation Drawer menu item clicks
        when (item.itemId) {
            // R.id.nav1 -> context!!.toast(getString(R.string.nav1_clicked))
            else -> true
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog

            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
            bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset > 0.1) {
                        close_imageview.visibility = View.VISIBLE
                    } else {
                        close_imageview.visibility = View.GONE
                    }
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> close_imageview.visibility = View.VISIBLE
                        else -> close_imageview.visibility = View.GONE
                    }
                }
            })
        }
        return dialog
    }

    companion object {
        fun newInstance(expenses: ArrayList<Expense>): NewEventBottomNavigationDrawerFragment {
            return NewEventBottomNavigationDrawerFragment(expenses)
        }
    }
}