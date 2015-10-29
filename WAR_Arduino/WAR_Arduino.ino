#include <avr/interrupt.h>
#include <avr/power.h>
#include <avr/sleep.h>

#include <avr/io.h>

#include <SoftwareSerialWAR.h> //does not use ISR(PCINT1) {}

#define BLE_WAKE (7)
#define LED (3)

#define BUFFER_SIZE (100)

#define WAIT (0)
#define PAIR (1)
#define BUTTON_PRESS (2)

SoftwareSerial mySerial(8, 9); // RX, TX
char ble_buffer[BUFFER_SIZE];
char newChar;
char lastChar;
int bufferLoc;
char ble_connected;
  
int mode;

void setup() 
{
  memset(ble_buffer, 0, BUFFER_SIZE / sizeof(ble_buffer[0]));
  ble_connected = 0;
  
  mode = WAIT;
  
  pinMode(BLE_WAKE, OUTPUT); //BLE WAKE pin
  digitalWrite(BLE_WAKE, HIGH);
  
  pinMode(PCINT8, INPUT); //Button
  digitalWrite(PCINT8, HIGH); //enable pull-up resistor
    
  pinMode(PCINT9, INPUT); //Button
  digitalWrite(PCINT9, HIGH); //enable pull-up resistor
  
  pinMode(LED, OUTPUT);  //LED 
  digitalWrite(LED, LOW); 
  setupBLE();
  
  cli();
  GIMSK |= B00100000; //enable PCINT11:8 interrupts
  sei();
  sleep();
}

void setupBLE(void)
{
  
  mySerial.begin(9600);

  mySerial.write("SET LNAME=WAR BUTTON\0");
  mySerial.write("\r");
  delay(100);

  mySerial.write("SET SNAME=WAR\0");
  mySerial.write("\r");
  delay(100);
  
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
  char lineEnd = 0;
  while(mySerial.available() > 0 && lineEnd == 0)
  {
    lastChar = newChar;
    newChar = mySerial.read();
    
    ble_buffer[bufferLoc] = newChar;
    bufferLoc++;
    
    if(newChar == '\r' && lastChar == '\n')
    {
      lineEnd = 1;
    }
  }
  
  if(lineEnd)
  {
    newChar = 0;
    lastChar = -1;
    
    if(bufferLoc >= 6 && memcmp(ble_buffer, "CON=OK", 6) == 0)
    {
      ble_connected = 1;
     
    }
    
    bufferLoc = 0;
    lineEnd = 0;
  }
  
  
  
  if(ble_connected == 1)
  {
    int wait_time = 1000;
    char ack_recieved = 0;
    
    bufferLoc = 0;
    mySerial.flush();
    digitalWrite(LED, HIGH);
    
    while(ack_recieved == 0)
    {
      if(mode == BUTTON_PRESS)
      {
        mySerial.write("SND BUTTON");
      }
      else if(mode == PAIR)
      {
        mySerial.write("SND PAIR");
      }
      
      mySerial.write("\r");
      
      delay(wait_time);
      
      char lineEnd = 0;
      while(mySerial.available() > 0 && lineEnd == 0)
      {
        lastChar = newChar;
        newChar = mySerial.read();
        
        ble_buffer[bufferLoc] = newChar;
        bufferLoc++;
        
        if(newChar == '\r' && lastChar == '\n')
        {
          lineEnd = 1;
        }
      }
      
      if(lineEnd)
      {
        newChar = 0;
        lastChar = -1;
        
        if(bufferLoc >= 7 && memcmp(ble_buffer, "RCV=ACK", 7) == 0)
        {
          ack_recieved = 1;
         
        }
        bufferLoc = 0;
        lineEnd = 0;
      }
    }
    sleep();
  }
  
}

void sleep(void)
{
  
  
  cli();
  PCMSK1 |= B00000011; //enbale PCINT8
  sei();
  ADCSRA &= ~(1<<ADEN); //disable ADC
   
  mode = WAIT;
  ble_connected = 0;
  digitalWrite(LED, LOW); //LED
  digitalWrite(BLE_WAKE, LOW); //Sleep BLE
  
  mySerial.write("DMT");
  mySerial.write("\r");
  delay(100);
   
  set_sleep_mode(SLEEP_MODE_PWR_DOWN);
  sleep_enable();
  sleep_mode();
  sleep_disable();
  
  //Wait for button to be released
  while(digitalRead(PCINT8) == LOW);
  delay(500);
}

ISR(PCINT1_vect)
{ 
  if(digitalRead(PCINT8) == LOW)
  {
    mode = BUTTON_PRESS;  
  }
  else if(digitalRead(PCINT9) == LOW)
  {
    mode = PAIR;
  }
  cli();
  PCMSK1 &= B11111100; //disable PCINT8
  sei();
  ADCSRA |= (1<<ADEN); //enable ADC
  
  //flush serial port of all data
  mySerial.flush();
  digitalWrite(BLE_WAKE, HIGH); //Wake BLE
  delay(100);
}
