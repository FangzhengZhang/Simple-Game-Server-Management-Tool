import { Component } from '@angular/core';
import {NgFor} from '@angular/common';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from "@angular/material/card";
import {HomeMessageCardDtoModel} from "./home.message-card-dto.model";
import {HomepageServiceService} from "../../services/homepage-service.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [MatGridListModule, NgFor,MatCardModule],
})

export class HomeComponent {
  homeMessages :HomeMessageCardDtoModel[] = [];

  constructor(private homepageService: HomepageServiceService) { }

  ngOnInit(): void {
    this.updateHomeMessageCard();
  }

  updateHomeMessageCard(): void {
    this.homepageService.getHomeMessageCardModel()
      .subscribe(homeMessages => this.homeMessages = homeMessages);
  }
}
