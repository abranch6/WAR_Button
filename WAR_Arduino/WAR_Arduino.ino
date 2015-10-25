#include <avr/interrupt.h>
#include <avr/power.h>
#include <avr/sleep.h>

#include <avr/io.h>

#include <SoftwareSerialWAR.h> //does not use ISR(PCINT1) {}

#define BLE_WAKE (7)
#define LED (3)

SoftwareSerial mySerial(8, 9); // RX, TX

void setup() 
{
  //all pins default as inputs with the internal pull-up resistor enabled.
  //DDRA = B00000000;
  //DDRB = B00000000;
 //PORTA = B11111111;
  //PORTB = B11111111;
  
  pinMode(BLE_WAKE, OUTPUT); //BLE WAKE pin
  digitalWrite(BLE_WAKE, HIGH);
  
  pinMode(PCINT8, INPUT); //Button
  digitalWrite(PCINT8, HIGH); //enable pull-up resistor
    
  pinMode(LED, OUTPUT);  //LED
  digitalWrite(LED, HIGH);
  
  setupBLE();
  
  cli();
  GIMSK |= B00100000; //enable PCINT11:8 interrupts
  sei();
  sleep();
}

void setupBLE(void)
{
  
  mySerial.begin(9600);

  mySerial.write("SET GPIO=OFF");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET LEDS=ON");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET WAKE=ON");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET WLVL=HIGH");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET SLEEP=OFF");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET ADVP=SLOW");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("SET CCON=ON");
  mySerial.write("\r");
  delay(100);
    
  mySerial.write("SET ADDR=000000000000");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("WRT");
  mySerial.write("\r");
  delay(100);
  
  mySerial.write("RST");
  mySerial.write("\r");
  delay(5000);
}

void loop() 
{
  if(digitalRead(PCINT8) == LOW)
  {
    while(digitalRead(PCINT8) == LOW);
    delay(500);
    sleep();
  }
}

void sleep(void)
{
 // mySerial.write("ADV OFF");
 // mySerial.write("\r");
 // delay(100);
  
  cli();
  PCMSK1 |= B00000001; //enbale PCINT8
  sei();
  ADCSRA &= ~(1<<ADEN); //disable ADC
   
  digitalWrite(LED, LOW); //Turn off LED
  digitalWrite(BLE_WAKE, LOW); //Sleep BLE
  
  mySerial.write("DMT");
  mySerial.write("\r");
  delay(100);
   
  set_sleep_mode(SLEEP_MODE_PWR_DOWN);
  sleep_enable();
  sleep_mode();
  sleep_disable();
  
  while(digitalRead(PCINT8) == LOW);
  delay(500);
}

ISR(PCINT1_vect)
{  
  cli();
  PCMSK1 &= B11111110; //disable PCINT8
  sei();
  ADCSRA |= (1<<ADEN); //enable ADC
  
  digitalWrite(LED, HIGH); //Turn on LED
  digitalWrite(BLE_WAKE, HIGH); //Wake BLE
  delay(100);
  
 // mySerial.write("ERR");
 // mySerial.write("\r");
}
