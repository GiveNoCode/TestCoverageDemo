package com.givenocode.data

import com.givenocode.domain.DataSource
import kotlin.random.Random

class LocalDataSource : DataSource {

    companion object {
        private val SENTENCES = listOf(
            "Aletopelta Pyroraptor Thecocoelurus Bugenasaura Dinodocus.",
            "Paluxysaurus Aragosaurus Krzyzanowskisaurus Helopus Juravenator.",
            "Wulatelong Asylosaurus Aerosteon Elachistosuchus Iuticosaurus.",
            "Priodontognathus Helopus Beishanlong Camptonotus Frenguellisaurus.",
            "Dongyangosaurus Aristosaurus Eolosaurus Protoavis Agnosphitys.",
            "Brachytrachelopan Gilmoreosaurus Hierosaurus Albertosaurus Alaskacephale.",
            "Hesperonychus Rapator Cristatusaurus Procheneosaurus Lagosuchus.",
            "Ceratops Dinodocus Cedarosaurus Cheneosaurus Euoplocephalus.",
            "Struthiosaurus Ganzhousaurus Shunosaurus Rayososaurus Elopteryx.",
            "Zhuchengtyrannus Cedarosaurus Prosaurolophus Alwalkeria Alamosaurus."
        )
    }

    override suspend fun getData(): String {
        return SENTENCES[Random.nextInt(SENTENCES.size)]
    }
}