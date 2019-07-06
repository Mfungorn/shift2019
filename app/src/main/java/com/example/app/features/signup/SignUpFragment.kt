package com.example.app.features.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.DefaultTextWatcher
import com.example.app.features.BaseFragment
import com.example.app.features.MainFlowFragment
import kotlinx.android.synthetic.main.fragment_signin.view.*
import kotlinx.android.synthetic.main.fragment_signup.view.*

class SignUpFragment : BaseFragment(), SingUpView {

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(getLayoutID(), container, false)

        v.edit_signup_name.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.name = s!!.toString()
            }
        })

        v.edit_signup_password.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.password = s!!.toString()
            }
        })

        v.edit_signup_login.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.login = s!!.toString()
            }
        })

        v.edit_signup_email.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.email = s!!.toString()
            }
        })

        v.edit_signup_phone.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.phone = s!!.toString()
            }
        })

        v.reguser_button.setOnClickListener {
            run {
                presenter.createUser()
            }
        }

        return v
    }

    override fun getLayoutID() = R.layout.fragment_signup

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun onUserSignUp() {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFlowFragment.newInstance(), "MAIN_FLOW_FRAGMENT")
            .commit()
    }
    companion object {
        fun newInstance() = SignUpFragment()

    }

}
