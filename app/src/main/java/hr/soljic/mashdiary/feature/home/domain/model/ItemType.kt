package hr.soljic.mashdiary.feature.home.domain.model

sealed class ItemType() {
    object Movie : ItemType() {
        override fun toString(): String {
            return "MOVIE"
        }
    }

    object TvShow : ItemType() {
        override fun toString(): String {
            return "TV_SHOW"
        }

    }

    object Album : ItemType() {
        override fun toString(): String {
            return "ALBUM"
        }
    }


}
