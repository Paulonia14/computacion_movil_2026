class MovieModels {
  bool adult;
  String? backdropPath;
  List<int> genreIds;
  int id;
  String originalLanguage;
  String originalTitle;
  String overview;
  String? posterPath;
  String releaseDate;
  String title;
  bool video;
  double voteAverage;
  int voteCount;
  MovieModels({
    required this.adult,
    this.backdropPath,
    required this.genreIds,
    required this.id,
    required this.originalLanguage,
    required this.originalTitle,
    required this.overview,
    this.posterPath,
    required this.releaseDate,
    required this.title,
    required this.video,
    required this.voteAverage,
    required this.voteCount,
  });

  factory MovieModels.fromJson(Map<String, dynamic> json) => MovieModels(
        adult: json["adult"] ?? false,
        backdropPath: json["backdrop_path"] ?? "",
        genreIds: List<int>.from(json["genre_ids"].map((x) => x)),
        id: json["id"] ?? 0,
        originalLanguage: json["original_language"] ?? "",
        originalTitle: json["original_title"] ?? "",
        overview: json["overview"] ?? "",
        posterPath: json["poster_path"] ?? "",
        releaseDate: json["release_date"] ?? "",
        title: json["title"] ?? "",
        video: json["video"] ?? false,
        voteAverage: json["vote_average"].toDouble() ?? 0.0,
        voteCount: json["vote_count"] ?? 0,
      );

      Map<String, dynamic> toJson() => {
        "adult": adult,
        "backdrop_path": backdropPath,
        "genre_ids": List<dynamic>.from(genreIds.map((x) => x)),
        "id": id,
        "original_language": originalLanguage,
        "original_title": originalTitle,
        "overview": overview,
        "poster_path": posterPath,
        "release_date": releaseDate,
        "title": title,
        "video": video,
        "vote_average": voteAverage,
        "vote_count": voteCount,
      };

      // empty constructor
      MovieModels.empty() : 
        adult = false,
        backdropPath = "",
        genreIds = [],
        id = 0,
        originalLanguage = "",
        originalTitle = "",
        overview = "",
        posterPath = "",
        releaseDate = "",
        title = "",
        video = false,
        voteAverage = 0.0,
        voteCount = 0;
}

// {
//   "adult": false,
//   "backdrop_path": "/2w4xG178RpB4MDAIfTkqAuSJzec.jpg",
//   "belongs_to_collection": {
//     "id": 10,
//     "name": "Star Wars Collection",
//     "poster_path": "/pWVLFh4OuejTpUaDQbB1C4zoS2p.jpg",
//     "backdrop_path": "/iY2ujEY2m68OTTlPFTiHub9joHS.jpg"
//   },
//   "budget": 11000000,
//   "genres": [
//     {
//       "id": 12,
//       "name": "Adventure"
//     },
//     {
//       "id": 28,
//       "name": "Action"
//     },
//     {
//       "id": 878,
//       "name": "Science Fiction"
//     }
//   ],
//   "homepage": "http://www.starwars.com/films/star-wars-episode-iv-a-new-hope",
//   "id": 11,
//   "imdb_id": "tt0076759",
//   "origin_country": [
//     "US"
//   ],
//   "original_language": "en",
//   "original_title": "Star Wars",
//   "overview": "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
//   "popularity": 20.6912,
//   "poster_path": "/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
//   "production_companies": [
//     {
//       "id": 1,
//       "logo_path": "/tlVSws0RvvtPBwViUyOFAO0vcQS.png",
//       "name": "Lucasfilm Ltd.",
//       "origin_country": "US"
//     },
//     {
//       "id": 25,
//       "logo_path": "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
//       "name": "20th Century Fox",
//       "origin_country": "US"
//     }
//   ],
//   "production_countries": [
//     {
//       "iso_3166_1": "US",
//       "name": "United States of America"
//     }
//   ],
//   "release_date": "1977-05-25",
//   "revenue": 775398007,
//   "runtime": 121,
//   "spoken_languages": [
//     {
//       "english_name": "English",
//       "iso_639_1": "en",
//       "name": "English"
//     }
//   ],
//   "status": "Released",
//   "tagline": "A long time ago in a galaxy far, far away...",
//   "title": "Star Wars",
//   "video": false,
//   "vote_average": 8.2,
//   "vote_count": 22061
// }