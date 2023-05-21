package com.myapplication.astonfive

object RepositoryContacts {
    private val contactslist = ArrayList<Contact>()

    init {
        val user1 = Contact(0,"Kirill","Ivanov","+79865357")
        val user2 = Contact(1,"Alexei","Ivanov","+798652323")
        val user3 = Contact(2,"Masha","Ivanova","+79865435345")
        val user4 = Contact(3,"Ivan","Ivanov","+798651231234")
        contactslist.add(user1)
        contactslist.add(user2)
        contactslist.add(user3)
        contactslist.add(user4)
    }
    fun getList() = contactslist

    fun getContact(userId:Int):Contact?{
        for (elem in contactslist){
            if (userId==elem.id){
                return elem
            }
        }
    return null}

    fun updateContact(userEdit:Contact){
        for (elem in contactslist){
            if (userEdit.id == elem.id){
                contactslist.set(userEdit.id,userEdit)
            }
        }
    }
}