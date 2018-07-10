package {{ cookiecutter.package_name }}.core.recyclerview

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.ViewGroup

import java.util.ArrayList

class SingleTypeAdapter<T> @JvmOverloads constructor(context: Context, @get:LayoutRes
protected var layoutRes: Int = 0) : BaseViewAdapter<T>(context) {

    interface Presenter<T> : BaseViewAdapter.Listener {
        fun onItemClick(t: T)
    }

    init {
        mCollection = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*> {
        return BindingViewHolder(
                DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutRes, parent, false))
    }

    override fun getItemCount(): Int {
        return mCollection!!.size
    }

    fun add(viewModel: T) {
        mCollection!!.add(viewModel)
        notifyDataSetChanged()
    }

    fun add(position: Int, viewModel: T) {
        mCollection!!.add(position, viewModel)
        notifyDataSetChanged()
    }

    fun set(viewModels: List<T>) {
        mCollection!!.clear()
        addAll(viewModels)
    }

    fun addAll(viewModels: List<T>) {
        mCollection!!.addAll(viewModels)
        notifyDataSetChanged()
    }
}