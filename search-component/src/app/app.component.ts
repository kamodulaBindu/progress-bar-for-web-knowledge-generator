import { Component } from '@angular/core';
import { WebSocketAPI } from './WebSocketAPI';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MicroServiceProgressService } from './micro-service-progress.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular8-springboot-websocket';

  name: string;
  greeting: string;
  message$: Observable<string>;
  message: string;

  color = 'primary';
  mode = 'determinate';
  value = 2;
  bufferValue = 75;
  
  constructor(private webSocketService: WebSocketAPI, private router: Router, private microServiceProgress: MicroServiceProgressService) {

  }

  ngOnInit() {
    this.message$ = this.webSocketService.data;
    this.message$.subscribe(data => {
      this.message = data;
    });
    this.webSocketService._connect();
  }

  fetchWebPage(){
    console.log();
    this.microServiceProgress.getWebData()
        .subscribe(data => {
          console.log(data)
        }
    );
    return true;
  }

  fetchLinks(){
    console.log();
    this.microServiceProgress.getLinks()
        .subscribe(data => console.log(data)
    );
    return true;
  }

  keyWordPhrase(){
    console.log();
    this.microServiceProgress.getKeyWords()
        .subscribe(data => console.log(data)
    );
    return true;
  }


  connect(){
   
  }

  disconnect(){
    this.webSocketService._disconnect();
  }

  sendMessage() {
    if(this.fetchLinks()) {
      this.value = 40;
    }
    if(this.fetchWebPage()) {
      this.value = 70;
    }
    if(this.keyWordPhrase()) {
      this.value = 100;
    }
    this.webSocketService._send(this.name);
  }

  // handleMessage(message) {
  //    if(JSON.parse(message).content.length != 0) {
  //     this.greeting = JSON.parse(message).content;
  //    }
  // }

}
