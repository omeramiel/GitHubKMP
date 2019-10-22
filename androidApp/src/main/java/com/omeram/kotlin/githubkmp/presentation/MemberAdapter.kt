package com.omeram.kotlin.githubkmp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omeram.kotlin.githubkmp.R
import com.omeram.kotlin.githubkmp.model.Member
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_member.view.*

class MemberAdapter(var members: List<Member>) :
    RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_member, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun getItemCount(): Int = members.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position])
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(member: Member) {
            Picasso.get().load(member.avatar_url).into(itemView.memberAvatar)
            itemView.memberName.text = member.login
        }
    }

}