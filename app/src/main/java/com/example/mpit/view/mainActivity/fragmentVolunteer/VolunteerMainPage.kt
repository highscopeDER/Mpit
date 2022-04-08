package com.example.mpit.view.mainActivity.fragmentVolunteer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mpit.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class VolunteerMainPage : MvpAppCompatFragment(), VolunteerMainInterface {

    @InjectPresenter
    lateinit var presenter: VolunteerMainPresenter
    lateinit var helpAskListView: RecyclerView
    lateinit var volunteerTopView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_volunteer_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        volunteerTopView = view.findViewById(R.id.volunteerTopView)
        volunteerTopView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        volunteerTopView.adapter = VolunteerTopAdapter()
        helpAskListView = view.findViewById(R.id.helpAskView)
        helpAskListView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        helpAskListView.adapter = HelpAskAdapter()
    }

}