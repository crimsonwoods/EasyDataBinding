package net.crimsonwoods.easydatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Simple Fragment for Testing
 */
class TestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // activity_list_item has 2 child views:
        // - ImageView: id = "icon"
        // - TextView:  id = "text1"
        return inflater.inflate(android.R.layout.activity_list_item, container, false)
    }
}
