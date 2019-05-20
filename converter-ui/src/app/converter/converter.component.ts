import { Component, OnInit, Inject } from '@angular/core';
import { Router } from "@angular/router";
import { SESSION_STORAGE, WebStorageService } from 'angular-webstorage-service';

import { AuthService } from '../auth.service';
import { ConversionUnit } from '../conversion-unit';
import { ConversionService } from '../conversion.service';
import { AuditorService } from '../auditor.service';

@Component({
  selector: 'app-converter',
  templateUrl: './converter.component.html',
  styleUrls: ['./converter.component.css']
})
export class ConverterComponent implements OnInit {

  // Distance conversion options
  readonly distanceOptions: ConversionUnit[] = [
    {key: "mm", label: "Millimetre"},
    {key: "cm", label: "Centimetre"},
    {key: "m", label: "Metre"},
    {key: "km", label: "Kilometre"},
    {key: "in", label: "Inch"},
    {key: "ft", label: "Foot"},
    {key: "yd", label: "Yard"},
    {key: "ml", label: "Mile"}
  ];
  distanceValue = "";

  // Area conversion options
  readonly areaOptions: ConversionUnit[] = [
    {key: "mm2", label: "Square Millimetre"},
    {key: "cm2", label: "Square Centimetre"},
    {key: "m2", label: "Square Metre"},
    {key: "in2", label: "Square Inch"},
    {key: "ft2", label: "Square Foot"},
    {key: "yd2", label: "Square Yard"}
  ];
  areaValue = "";

  // Volume conversion options
  readonly volumeOptions: ConversionUnit[] = [
    {key: "ml", label: "Millilitre"},
    {key: "l", label: "Litre"},
    {key: "oz", label: "Ounce"},
    {key: "pt", label: "Pint"},
    {key: "gal", label: "Gallon"}
  ];
  volumeValue = "";

  // Mass conversion options
  readonly massOptions: ConversionUnit[] = [
    {key: "mg", label: "Milligram"},
    {key: "g", label: "Gram"},
    {key: "kg", label: "Kilogram"},
    {key: "oz", label: "Ounce"},
    {key: "lb", label: "Pound"},
    {key: "s", label: "Stone"}
  ];
  massValue = "";

  // Temperature conversion options
  readonly temperatureOptions: ConversionUnit[] = [
    {key: "c", label: "Celsius"},
    {key: "f", label: "Fahrenheit"}
  ];
  temperatureValue = "";
  temperatureValueLabel = "Fahrenheit";

  constructor(private conversionService: ConversionService,
    private auditorService: AuditorService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: WebStorageService) { }

  ngOnInit() {
    // If there isnt a session ID, redirect to login.
    console.log('sessionID: '+this.storage.get('sessionID'));
    if (!this.storage.get('sessionID')) {
      this.router.navigate(['/']);
    }
  }

  /* Calls the service to convert the distance */
  convertDistance(value: string, fromKey: string, toKey: string): void {
    this.conversionService.doConversion('distance', value, fromKey, toKey)
      .subscribe(response => {
        if (!response) {
            this.distanceValue = "";
            return;
        }
        this.distanceValue = response.content;
      });
    this.logAction("Converted distance: "+value+" "+fromKey+" => "+toKey);
  }

  /* Calls the service to convert the area */
  convertArea(value: string, fromKey: string, toKey: string): void {
    this.conversionService.doConversion('area', value, fromKey, toKey)
      .subscribe(response => {
        if (!response) {
            this.areaValue = "";
            return;
        }
        this.areaValue = response.content;
      });
    this.logAction("Converted area: "+value+" "+fromKey+" => "+toKey);
  }

  /* Calls the service to convert the volume */
  convertVolume(value: string, fromKey: string, toKey: string): void {
    this.conversionService.doConversion('volume', value, fromKey, toKey)
      .subscribe(response => {
        if (!response) {
            this.volumeValue = "";
            return;
        }
        this.volumeValue = response.content;
      });
    this.logAction("Converted volume: "+value+" "+fromKey+" => "+toKey);
  }

  /* Calls the service to convert the mass */
  convertMass(value: string, fromKey: string, toKey: string): void {
    this.conversionService.doConversion('mass', value, fromKey, toKey)
      .subscribe(response => {
        if (!response) {
            this.massValue = "";
            return;
        }
        this.massValue = response.content;
      });
    this.logAction("Converted mass: "+value+" "+fromKey+" => "+toKey);
  }

  /* Calls the service to convert the temperature */
  convertTemperature(value: string, fromKey: string): void {
    this.conversionService.doTemperatureConversion(value, fromKey)
      .subscribe(response => {
        if (!response) {
            this.temperatureValue = "";
            return;
        }
        this.temperatureValue = response.content;
      });
    this.logAction("Converted temperature: "+value+" => "+fromKey);
  }

  /* This changes the converted unit label, and calls the converter. */
  fromTempChanged(value: string, newUnit: string): void {
    if (newUnit == 'c') {
      this.temperatureValueLabel = "Fahrenheit";
    } else if (newUnit == 'f') {
      this.temperatureValueLabel = "Celsius";
    }
    this.logAction("Changed temperature unit: "+newUnit);
    this.convertTemperature(value, newUnit);
  }

  /* Clears the current sessionID and returns to the login screen. */
  logout(): void {
    this.auditorService.logAction("Logged Out")
      .subscribe(response => {
        this.storage.set('sessionID', null);
        this.router.navigate(['/']);
      });
  }

  /* Used to log most actions performed by the user. */
  private logAction(action: string) {
    this.auditorService.logAction(action)
      .subscribe(response => {
        // Do nothig
      });
  }
}
