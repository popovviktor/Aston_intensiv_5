package com.myapplication.astonfive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView


class FragmentForList : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contacts = RepositoryContacts.getList()
        clearBackStack()
        addCotactsInLinearLayout(view,contacts)
    }
    fun clearBackStack(){
        parentFragmentManager.popBackStack()
    }
    fun addCotactsInLinearLayout(view: View, contacts:ArrayList<Contact>){
        val linear_for_contacts = view.findViewById<LinearLayout>(R.id.linear_lt_for_items)
        for (i in 0 ..contacts.size-1){
            val contact_item = contacts[i]
            initContactItemFieldsAndClickListener(linear_for_contacts,contact_item)
        }
    }
    fun initContactItemFieldsAndClickListener(viewGroup:ViewGroup, contact_item:Contact){
        val view_item_contact = LayoutInflater.from(activity)
            .inflate(R.layout.item,viewGroup,false)
        val name = view_item_contact.findViewById<TextView>(R.id.item_name)
        val surname = view_item_contact.findViewById<TextView>(R.id.item_surname)
        val phone = view_item_contact.findViewById<TextView>(R.id.item_phone_number)
        name.text = contact_item.name
        surname.text = contact_item.surname
        phone.text = contact_item.phone_number
        val id = contact_item.id
        viewGroup.addView(view_item_contact)
        view_item_contact.setOnClickListener {
            startFragmentEditContactWithBundleIdItem(id)
        }
    }
    fun startFragmentEditContactWithBundleIdItem(id_contact:Int){
        val fragForEdit = FragmentForEditItem()
        val bundle = Bundle()
        bundle.putInt("iditem",id_contact)
        fragForEdit.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragForEdit)
            .addToBackStack(null)
            .commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_for_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentForList()
    }
}