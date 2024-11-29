package com.example.guessword.data

import com.example.guessword.R

class dataset {
    fun getAnglaisExamples(): List<Picture> {

        return listOf(
            Picture(listOf(R.drawable.sun,R.drawable.plus, R.drawable.flower), "sunflower", R.drawable.sunflower),
            Picture(listOf(R.drawable.rain, R.drawable.plus,R.drawable.bow), "rainbow", R.drawable.rainbow),
            Picture(listOf(R.drawable.fire,R.drawable.plus, R.drawable.man), "fireman", R.drawable.fireman),
            Picture(listOf(R.drawable.cup, R.drawable.plus,R.drawable.cake), "cupcake", R.drawable.cupcake),
            Picture(listOf(R.drawable.water,R.drawable.plus, R.drawable.melon), "watermelon", R.drawable.watermelon),
            Picture(listOf(R.drawable.key, R.drawable.plus,R.drawable.board), "keyboard", R.drawable.keyboard),
            Picture(listOf(R.drawable.snow, R.drawable.plus,R.drawable.man), "snowman", R.drawable.snowman),
            Picture(listOf(R.drawable.honey,R.drawable.plus, R.drawable.moon), "honeymoon", R.drawable.honeymoon),
            Picture(listOf(R.drawable.hand, R.drawable.plus,R.drawable.bag), "handbag", R.drawable.handbag),
            Picture(listOf(R.drawable.hair, R.drawable.plus,R.drawable.brush), "hairbrush", R.drawable.hairbrush),
            Picture(listOf(R.drawable.butter, R.drawable.plus,R.drawable.fly), "butterfly", R.drawable.butterfly),
            Picture(listOf(R.drawable.bascket,R.drawable.plus, R.drawable.ball), "basketball", R.drawable.basketball),

        )
    }

}
