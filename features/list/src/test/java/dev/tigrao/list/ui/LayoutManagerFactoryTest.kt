package dev.tigrao.list.ui

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.tigrao.list.ui.LayoutManagerFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class LayoutManagerFactoryTest {

    @Test
    fun givenLandScapeOrientation_ShouldCreateGridLayoutManager() {
        val resources = mockk<Resources>()
        val context = mockk<Context>()
        val configuration = Configuration()
        configuration.orientation = Configuration.ORIENTATION_LANDSCAPE

        every { context.resources } returns resources
        every { resources.configuration } returns configuration

        val result = LayoutManagerFactory().getInstance(context)

        assertTrue(result is GridLayoutManager)
    }

    @Test
    fun givenPortraitOrientation_ShouldCreateLinearLayoutManager() {
        val resources = mockk<Resources>()
        val context = mockk<Context>()
        val configuration = Configuration()
        configuration.orientation = Configuration.ORIENTATION_PORTRAIT

        every { context.resources } returns resources
        every { resources.configuration } returns configuration

        val result = LayoutManagerFactory().getInstance(context)

        assertTrue(result is LinearLayoutManager)
    }
}
