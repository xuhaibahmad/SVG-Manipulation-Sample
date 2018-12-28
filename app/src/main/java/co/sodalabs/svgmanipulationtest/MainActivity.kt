package co.sodalabs.svgmanipulationtest

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.devs.vectorchildfinder.VectorChildFinder
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.image
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val colors by lazy {
        listOf(
            android.R.color.holo_blue_dark,
            android.R.color.holo_blue_light,
            android.R.color.holo_green_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_dark,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_dark,
            android.R.color.holo_red_light,
            android.R.color.holo_purple
        )
    }

    private val vector by lazy { VectorChildFinder(this, R.drawable.ic_world_map, image) }

    private val paths by lazy {
        (1..68)
            .map { "path$it" }
            .mapNotNull { vector.findPathByName(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            randomizePathColors()
        }
    }

    private fun randomizePathColors() {
        paths.forEach {
            val color = getRandomColor()
            it.fillColor = color
        }
        image.invalidate()
    }

    private fun getRandomColor(): Int {
        val number = Random.nextInt(0, 10)
        val color = colors[number % colors.size]
        return ContextCompat.getColor(this, color)
    }
}
