package com.myapplication.astonfive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager


class FragmentForList : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contacts = RepositoryContacts.getList()
        addCotactsInLinearLayout(view,contacts)
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
        bundle.putInt(Constants.ID_KEY,id_contact)
        fragForEdit.arguments = bundle
        val smallestScreenDp = requireActivity().resources.configuration.smallestScreenWidthDp
        if (smallestScreenDp>Constants.MIN_DP_FOR_TABLE){
            startForTableMode(fragForEdit)
        }else{
            settingForPortaitAndLansdscapeForPhone(fragForEdit)
        }
    }
    fun settingForPortaitAndLansdscapeForPhone(fragmentEdit:Fragment){
        val orientation = requireActivity().resources.configuration.orientation
        if (orientation==Constants.LANDSCAPE_ORIENTATION){
            startForLandscapeMode(fragmentEdit)
        }else{
            startPortraitMode(fragmentEdit)
        }
    }
    fun startPortraitMode(fragmentEdit:Fragment){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragmentEdit)
            .addToBackStack(null)
            .commit()
    }
    fun startForLandscapeMode(fragmentEdit:Fragment){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container2,fragmentEdit)
            .addToBackStack(null)
            .commit()
    }
    fun startForTableMode(fragmentEdit:Fragment){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container3,fragmentEdit)
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