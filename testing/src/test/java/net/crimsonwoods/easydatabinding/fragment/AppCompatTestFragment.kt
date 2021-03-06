package net.crimsonwoods.easydatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.crimsonwoods.easydatabinding.testing.R

/**
 * Simple Fragment for Testing (Using views of AppCompat)
 */
class AppCompatTestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment_appcompat, container, false)
    }
}
