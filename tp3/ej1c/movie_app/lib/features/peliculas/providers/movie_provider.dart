import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:movie_app/features/peliculas/data/models/movie_models.dart';
import 'package:movie_app/features/peliculas/data/models/now_playing.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

class MovieProvider extends ChangeNotifier{
  final String apiKey = dotenv.env['TMDB_API_KEY'] ?? '';
  final String baseUrl = "api.themoviedb.org";
  final String language = "es-ES";

  List<MovieModels> onDisplayMovies = [];
  List<MovieModels> popularMovies = [];
  List<MovieModels> topRatedMovies = [];
  List<MovieModels> upcomingMovies = [];

  MovieProvider() {
    getOnDisplayMovies();
    // getPopularMovies();
    // getTopRatedMovies();
    // getUpcomingMovies();
  }

  Future<String> _getJsonData(String endpoint, [int page =  1]) async {
    final url = Uri.https(baseUrl, endpoint, {
      'api_key': apiKey,
      'language': language,
      'page': "$page"
    });

    final response = await Future.delayed(const Duration(seconds: 2), ()  {
      return "Respuesta de $url";
    });

    return response;
  }

  // mostrar peliculas en pantalla
  Future<void> getOnDisplayMovies() async {
    final jsonData = await _getJsonData("/3/movie/now_playing");
    final nowPlayingResponse = NowPlayingModels.fromJson(json.decode(jsonData));
    onDisplayMovies = nowPlayingResponse.results;
    notifyListeners();
  }
  // Future<void> getPopularMovies() async {
  //   final jsonData = await _getJsonData("/3/movie/popular");
  //   final nowPlayingResponse = NowPlayingModels.fromJson(json.decode(jsonData));
  //   popularMovies = nowPlayingResponse.results;
  //   notifyListeners();
  // }
  // Future<void> getTopRatedMovies() async {
  //   final jsonData = await _getJsonData("/3/movie/top_rated");
  //   final nowPlayingResponse = NowPlayingModels.fromJson(json.decode(jsonData));
  //   topRatedMovies = nowPlayingResponse.results;
  //   notifyListeners();
  // }
  // Future<void> getUpcomingMovies() async {
  //   final jsonData = await _getJsonData("/3/movie/upcoming");
  //   final nowPlayingResponse = NowPlayingModels.fromJson(json.decode(jsonData));
  //   upcomingMovies = nowPlayingResponse.results;
  //   notifyListeners();
  // }
}