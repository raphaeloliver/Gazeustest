package com.example.gazeusapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gazeusapp.R
import com.example.gazeuslibrary.models.UserRepos
import kotlinx.android.synthetic.main.item_user_repo.view.*

class RepoNameAdapter(private val items : MutableList<UserRepos>?,

                      private val onRepoClickListener: OnRepoClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    inner class ViewHolderRepo(view : View) : RecyclerView.ViewHolder(view) {

        fun bindRepo(userRepos: UserRepos) {

                itemView.txtIdCode_Vl.text = userRepos.id.toString()
            itemView.txtNodeId_Vl.text = userRepos.nodeId
            itemView.txtName_Vl.text = userRepos.name
            itemView.txtFullName_Vl.text = userRepos.fullName
            itemView.txtOwnerVl.text = userRepos.owner.login

            itemView.setOnClickListener {
                onRepoClickListener.onClickItemRepo(userRepos)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolderRepo(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_user_repo,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val repo = items?.get(position)
        if(repo != null) {
            val holder = holder as ViewHolderRepo
            holder.bindRepo(repo)
        }

    }

    override fun getItemCount(): Int {
        return items?.count() ?: 0
    }

    interface OnRepoClickListener {
        fun onClickItemRepo(repos: UserRepos)
    }
}