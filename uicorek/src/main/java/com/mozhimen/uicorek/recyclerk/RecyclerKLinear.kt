package com.mozhimen.uicorek.recyclerk

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozhimen.basick.mok.MoKKey
import com.mozhimen.uicorek.R
import com.mozhimen.uicorek.databinding.RecyclerkLinearItemBinding

/**
 * @ClassName ViewKRecyclerLinear
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/9/21 15:04
 * @Version 1.0
 */
typealias IRecyclerKLinearListener = (position: Int, item: MoKKey) -> Unit

class RecyclerKLinear @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RecyclerView(context, attrs, defStyleAttr) {

    private var _viewKRecyclerLinearListener: IRecyclerKLinearListener? = null
    private val _adapter = RecyclerKAdapter(context)
    private val _keys = ArrayList<MoKKey>()

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = RecyclerKAdapter(context)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    /**
     * 设置监听器
     * @param listener Function2<[@kotlin.ParameterName] Int, MoKKey, Unit>
     */
    fun setOnItemClickListener(listener: IRecyclerKLinearListener) {
        _viewKRecyclerLinearListener = listener
    }

    /**
     * 绑定数据
     * @param keys List<MoKKey>
     * @param listener Function2<[@kotlin.ParameterName] Int, MoKKey, Unit>
     */
    fun bindKeys(keys: List<MoKKey>, listener: IRecyclerKLinearListener) {
        _keys.addAll(keys)
        val keyItems = mutableListOf<RecyclerKLinearItem>()
        keys.forEach {
            keyItems.add(RecyclerKLinearItem(it, listener))
        }
        _adapter.clearItems()
        _adapter.addItems(keyItems, true)
    }

    /**
     * 清除数据
     */
    fun clearKeys() {
        _keys.clear()
        _adapter.clearItems()
    }

    /**
     * 增加字段
     * @param key MoKKey
     */
    fun addKey(key: MoKKey) {
        _keys.add(key)
        _adapter.addItem(RecyclerKLinearItem(key, _viewKRecyclerLinearListener), true)
    }

    /**
     * 删除字段
     * @param key MoKKey
     */
    fun removeKey(key: MoKKey) {
        val index = _keys.indexOf(key)
        if (index in 0 until _keys.size) removeKey(index)
    }

    /**
     * 删除字段
     * @param index Int
     */
    fun removeKey(index: Int) {
        if (index in 0 until _keys.size) {
            _keys.removeAt(index)
            _adapter.removeItemAt(index)
        }
    }

    private inner class RecyclerKLinearItem(
        private val _dataKKey: MoKKey,
        private val _listener: IRecyclerKLinearListener?
    ) : RecyclerKItem<MoKKey, RecyclerKVBViewHolder<RecyclerkLinearItemBinding>>() {

        override fun onBindData(holder: RecyclerKVBViewHolder<RecyclerkLinearItemBinding>, position: Int) {
            holder.vb.layoutkRecyclerLinearItemTxt.text = _dataKKey.key
            holder.itemView.setOnClickListener {
                _listener?.invoke(position, _dataKKey)
            }
        }

        override fun getItemLayoutRes(): Int {
            return R.layout.recyclerk_linear_item
        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerKVBViewHolder<RecyclerkLinearItemBinding> {
            return RecyclerKVBViewHolder(LayoutInflater.from(parent.context).inflate(getItemLayoutRes(), parent, false))
        }
    }
}