package com.example.app.features.event

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.app.R
import com.example.app.core.MvpBottomSheetDialogFragment
import com.example.app.core.model.Expense
import com.example.app.features.event_new.BottomNavigationDrawerFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_event_bottom_navigation_drawer.*
import kotlinx.android.synthetic.main.fragment_event_bottom_navigation_drawer.view.*
import kotlinx.android.synthetic.main.fragment_new_event_bottom_navigation_drawer.*

class BottomNavigationDrawerFragment(private var expenses: ArrayList<Expense>) : MvpBottomSheetDialogFragment(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationView: NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        navigationView = view.navigation_view_event
        navigationView.setNavigationItemSelectedListener(this)

        expenses.forEach {
            navigationView.menu.add("${it.name}: ${it.cost} ${getString(R.string.currency)}")
        }
        return view
    }

    private fun getLayoutID(): Int = R.layout.fragment_event_bottom_navigation_drawer

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        close_imageview.setOnClickListener {
            this.dismiss()
        }
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
                        close_imageview_event.visibility = View.VISIBLE
                    } else {
                        close_imageview_event.visibility = View.GONE
                    }
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        else -> close_imageview_event.visibility = View.GONE
                    }
                }
            })
        }
        return dialog
    }

    companion object {
        fun newInstance(expenses: ArrayList<Expense>): BottomNavigationDrawerFragment {
            return BottomNavigationDrawerFragment(expenses)
        }
    }
}