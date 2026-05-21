import 'package:movie_app/features/peliculas/data/models/movie_models.dart';

class NowPlayingModels {
  Dates dates;
  int page;
  List<MovieModels> results;
  int totalPages;
  int totalResults;
  NowPlayingModels({
    required this.dates,
    required this.page,
    required this.results,
    required this.totalPages,
    required this.totalResults,
  });

  factory NowPlayingModels.fromJson(Map<String, dynamic> json) => NowPlayingModels(
        dates: Dates.fromJson(json["dates"]),
        page: json["page"] ?? 0,
        results: List<MovieModels>.from(
            json["results"].map((x) => MovieModels.fromJson(x))),
        totalPages: json["total_pages"] ?? 0,
        totalResults: json["total_results"] ?? 0,
      );
}

class Dates {
  DateTime maximum;
  DateTime minimum;
  Dates({
    required this.maximum,
    required this.minimum,
  });
  factory Dates.fromJson(Map<String, dynamic> json){
    return Dates(
        maximum: DateTime.parse(json["maximum"]),
        minimum: DateTime.parse(json["minimum"]),
      );
  }
}