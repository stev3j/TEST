package com.example.roomtest.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomtest.R
import com.example.roomtest.model.User
import com.example.roomtest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

//어떻게 이 프래그먼트를 불러오는거지?
class UpdateFragment: Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    //시작하면
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view == fragment_update라는 레이아웃
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        //mUserViewModel 초기화
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //각 칸에 원래 있던 내용들을 출력함
        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())

        //버튼을 클릭했을 때
        view.update_btn.setOnClickListener{
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }


    private fun updateItem(){
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(firstName, lastName, updateAge_et.text)){
            //Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            //Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            //화면 이동
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    //다 값이 들어가있는지 확인
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        //TextUtils.isEmpty() : null체크
        return !(TextUtils.isEmpty(firstName)) && !(TextUtils.isEmpty(lastName)) && !(age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //item(MenuItem).itemId가 menu_delete라면 deleteUser()를 실행
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onContextItemSelected(item)
    }

    //
    private fun deleteUser() {
        //경고창? 띄우기
        val builder = AlertDialog.Builder(requireContext())
        //Yes를 클릭했을 때
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "${args.currentUser.firstName} 이(가) 삭제되었습니다", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        }
        //No를 클릭했을 때
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("${args.currentUser.firstName} 을(를) 삭제하시겠습니까?")
        builder.setMessage("")
        builder.create().show()
    }
}