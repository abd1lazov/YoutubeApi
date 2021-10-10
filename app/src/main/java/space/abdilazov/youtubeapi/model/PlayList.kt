package space.abdilazov.youtubeapi.model


data class PlayList(
    var kind: String? = null,
    var etag: String? = null,
    var items: ArrayList<PlayListItems>? = null,
    var snippet: Snippet? = null
)

data class PlayListItems(
    var kind: String? = null,
    var etag: String? = null,
    var snippet: Snippet? = null,
    var contentDetails: ContentDetails? = null
)

data class ContentDetails(
    var itemCount: Int? = null
)

data class Snippet(
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var default: Default? = null
)

data class Thumbnails(
    var default: Default? = null,
    var url: String? = null
)

data class Default(
    var url: String? = null
)
