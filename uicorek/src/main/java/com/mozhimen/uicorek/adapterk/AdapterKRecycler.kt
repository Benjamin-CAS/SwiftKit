package com.mozhimen.uicorek.adapterk

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @ClassName RecyclerAdapterK
 * @Description TODO
 * @Author Kolin Zhao
 * @Date 2021/6/4 20:07
 * @Version 1.0
 */
/**
 * 作用: 通用RecyclerView适配器
 * 用法: viewBinding.mainList.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
 * val adapter: RecyclerAdapterK<User> = object : RecyclerAdapterK<User>(viewModel.userList, R.layout.item_user, BR.item) {
 *  override fun addListener(view: View, itemData: User, position: Int) {
 *      (view.findViewById(R.id.user_pane) as LinearLayout).setOnClickListener {
 *          //逻辑
 * }}}
 * viewBinding.mainList.adapter=adapter
 */
open class AdapterKRecycler<T>(
    private var itemDatas: List<T>,
    private val defaultLayout: Int,
    private val brId: Int
) : RecyclerView.Adapter<AdapterKRecycler.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun getItemCount() = if (itemDatas.isNullOrEmpty()) 0 else itemDatas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding.setVariable(brId, itemDatas[position])
        addListener(holder.binding.root, itemDatas[position], position)
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int) = getItemLayout(itemDatas[position])

    //region #自定义方法
    class BaseViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun getItemLayout(itemData: T) = defaultLayout

    open fun addListener(view: View, itemData: T, position: Int) {}

    @SuppressLint("NotifyDataSetChanged")
    fun onItemDataChanged(newItemDatas: List<T>) {
        itemDatas = newItemDatas
        notifyDataSetChanged()
    }

    fun onItemRangeChanged(newItemDatas: List<T>, positionStart: Int, itemCount: Int) {
        itemDatas = newItemDatas
        notifyItemChanged(positionStart, itemCount)
    }

    fun onItemRangeInserted(newItemDatas: List<T>, positionStart: Int, itemCount: Int) {
        itemDatas = newItemDatas
        notifyItemRangeInserted(positionStart, itemCount)
    }

    fun onItemRangeRemoved(newItemDatas: List<T>, positionStart: Int, itemCount: Int) {
        itemDatas = newItemDatas
        notifyItemRangeRemoved(positionStart, itemCount)
    }
    //endregion
}