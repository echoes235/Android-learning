package com.example.fragmenttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class LeftFragment:Fragment()
{
    companion object
    {
        const val REQ_RIGHT="REQ_RIGHT"
        const val KEY_ACTION="KEY_ACTION"
        const val ACTION_SHOW_ANOTHER="SHOW_ANOTHER"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.left_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val button=view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQ_RIGHT,
                Bundle().apply{putString(KEY_ACTION, ACTION_SHOW_ANOTHER)}
            )
        }
    }
}