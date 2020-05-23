package com.example.searchsuggestions.ui.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchsuggestions.R
import com.example.searchsuggestions.data.response.Address
import com.example.searchsuggestions.util.AppUtils.showToast
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {
    private var mAddressViewModel: AddressViewModel? = null
    private lateinit var mAdapter: AddressAdapter
    private var mAddressList: List<Address> = ArrayList<Address>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        mAddressViewModel?.init()

        initRecyclerView()
        bindSearchView()
        observeData()
    }

    private fun initRecyclerView() {
        mAdapter = AddressAdapter(this, mAddressList)
        val manager = LinearLayoutManager(this)
        recyclerView?.layoutManager = manager
        recyclerView?.adapter = mAdapter
    }

    private fun bindSearchView() {
        editTextSearch?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.length > 2) {
                    progress_bar?.visibility = View.VISIBLE
                    recyclerView?.visibility = View.GONE
                    mAddressViewModel?.apiCall(s.toString(), "")
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun observeData() {
        mAddressViewModel?.getLiveData()?.observe(
            this,
            Observer {
                progress_bar?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE

                if (it != null) {
                    var addressList = it!!.data.addressList
                    mAdapter.setData(addressList)
                } else {
                    showToast(this, getString(R.string.request_fail_msg))
                }
            }
        )
    }

    @Override
    override fun onStop() {
        super.onStop()
    }
}
