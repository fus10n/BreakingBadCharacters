package com.example.breakingbadcharacters

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.breakingbadcharacters.content.ContentManager

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun parseJsonResponse() {
        // Context of the app under test.
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val dataStream = context.resources.openRawResource(R.raw.test_data)
        val jsonArray = ContentManager.parseResponse(dataStream)
        assertNotNull(jsonArray)
        assert(jsonArray!!.charactersArray.isNotEmpty())
    }

}