package {{ cookiecutter.package_name }}.core.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import {{ cookiecutter.package_name }}.BR


abstract class BaseViewAdapter<T>(context: Context) : RecyclerView.Adapter<BindingViewHolder<*>>() {

    protected val mLayoutInflater: LayoutInflater

    protected var mCollection: MutableList<T>? = null
    protected var listener: Listener? = null
    protected var mDecorator: Decorator? = null

    interface Listener

    interface Decorator {
        fun decorator(holder: BindingViewHolder<*>, position: Int, viewType: Int)
    }

    init {
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onBindViewHolder(holder: BindingViewHolder<*>, position: Int) {
        val item = mCollection!![position]
        holder.binding.setVariable(BR.item, item)
        holder.binding.setVariable(BR.listener, listener)
        holder.binding.executePendingBindings()
        if (mDecorator != null) {
            mDecorator!!.decorator(holder, position, getItemViewType(position))
        }
    }

    override fun getItemCount(): Int {
        return mCollection!!.size
    }

    open fun remove(position: Int) {
        mCollection!!.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun clear() {
        mCollection!!.clear()
        notifyDataSetChanged()
    }

    fun setDecorator(decorator: Decorator) {
        mDecorator = decorator
    }
}
