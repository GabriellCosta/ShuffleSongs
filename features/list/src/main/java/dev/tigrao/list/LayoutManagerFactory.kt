package dev.tigrao.list

import android.content.Context
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

private const val NUMBER_OF_SPAN = 2

internal class LayoutManagerFactory {

    fun getInstance(context: Context) =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            GridLayoutManager(context, NUMBER_OF_SPAN)
        else
            LinearLayoutManager(context)
}
