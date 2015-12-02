#include <avr/interrupt.h>
#include <avr/power.h>
#include <avr/sleep.h>

#include <avr/io.h>

#include <SoftwareSerialWAR.h> //does not use ISR(PCINT1) {}

#define BLE_WAKE (7)
#define LED (3)

#define BUFFER_SIZE (100)
#define READ_TIMEOUT (2000)
#define PAIR_TIMEOUT (60000)
#define BUTTON_TIMEOUT (600000)


#define WAIT (0)
#define PAIR (1)
#define BUTTON_PRESS (2)


SoftwareSerial mySerial(8, 9); // RX, TX

//variables for reading from the bluetooth device
char ble_buffer[BUFFER_SIZE];
char newChar;
char lastChar;
int bufferLoc;

char bleConnected;

//current mode of the device (WAIT, PAIR, BUTTON)  
int mode;

long wakeTime;
void setup() 
{
  
//  digitalWrite(PCINT1, HIGH);
//  digitalWrite(PCINT2, HIGH);
  
  memset(ble_buffer, 0, BUFFER_SIZE / sizeof(ble_buffer[0]));
  bleConnected = 0;
  
  mode = WAIT;
  
  pinMode(BLE_WAKE, OUTPUT); //BLE WAKE pin
  digitalWrite(BLE_WAKE, HIGH);
  
  pinMode(PCINT8, INPUT); //Button
  digitalWrite(PCINT8, HIGH); //enable pull-up resistor
    
  pinMode(PCINT9, INPUT); //Button
  digitalWrite(PCINT9, HIGH); //enable pull-up resistor
  
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

  //set all variables on the BLE module
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
  //call function based on which button is pressed
  if(mode == PAIR)
  {
    rearButtonPressed();
  }
  else if(mode == BUTTON_PRESS)
  {
    frontButtonPressed();
  }
  sleep(); //back to sleep
}

void frontButtonPressed(void) //called if the front button on the device is pressed
{
  char done = 0;
  
  while(done == 0)
  {
    //wait for BLE to connect to phone
    if(bleConnected == 0)
    {
      readBLE();
      if(bufferLoc >= 6 && memcmp(ble_buffer, "CON=OK", 6) == 0)
      {
        bleConnected = 1;
      }
    }
    else
    {
      int wait_time = 1000;
      char ack_recieved = 0;
      
      mySerial.flush();
      digitalWrite(LED, HIGH);
      
      while(ack_recieved == 0 && millis() - wakeTime > BUTTON_TIMEOUT) //resends command until ack is seen or until timeout is reached
      {
        //send command
        mySerial.write("SND BUTTON");
        mySerial.write("\r");
        
        delay(wait_time);
        
        readBLE();
  
        if(bufferLoc >= 7 && memcmp(ble_buffer, "RCV=ACK", 7) == 0) //check for ack
        {
          ack_recieved = 1;
         
        }
      }
      done = 1;
    }
  
    if(millis() - wakeTime > BUTTON_TIMEOUT) //sleep if timeout is reached
    {
      done = 1;
    }
  }
}

void rearButtonPressed(void) //called if the rear button on the device is pressed
{
  char done = 0;
  int wait_time = 1000; //time to wait until check for response
  char ack_recieved = 0;
  
  while(done == 0)
  {
    //wait for BLE to connect to phone
    if(bleConnected == 0)
    {
      readBLE();
      if(bufferLoc >= 6 && memcmp(ble_buffer, "CON=OK", 6) == 0)
      {
        bleConnected = 1;
        mySerial.flush();
      }
    }
    else
    {
      mySerial.write("SND PAIR"); 
      mySerial.write("\r"); //send message
      
      delay(wait_time);
      
      readBLE(); //read in response, waiting for ack

      if(bufferLoc >= 7 && memcmp(ble_buffer, "RCV=ACK", 7) == 0) //check for ack
      {
        ack_recieved = 1;
        done = 1;
      }
    }
    
    if(millis() - wakeTime > PAIR_TIMEOUT) //sleep if timeout is reached
    {
      done = 1;
    }
  }
}

void readBLE(void)
{
  newChar = 0;
  lastChar = -1;
  
  char lineEnd = 0;
  bufferLoc = 0;
  
  long startTime = millis();
  long endTime = millis();
  
  while(endTime - startTime < READ_TIMEOUT && lineEnd == 0) //read until timeout or end of line
  {
    if(mySerial.available() > 0) //make sure read is available
    {
      lastChar = newChar;
      newChar = mySerial.read(); //read in char and save previous
    
      ble_buffer[bufferLoc] = newChar; //add to buffer
      bufferLoc++;
    
      if(newChar == '\r' && lastChar == '\n') //check for end of line
      {
        lineEnd = 1;
      }
    }
    endTime = millis();
  }
  
  if(endTime - startTime >= READ_TIMEOUT) //if timed out buffer is invalid
  {
    bufferLoc = 0;
  }
}

void sleep(void)
{
  cli();
  PCMSK1 |= B00000011; //enbale PCINT8
  sei();
  ADCSRA &= ~(1<<ADEN); //disable ADC
   
  mode = WAIT;
  bleConnected = 0;
  digitalWrite(LED, LOW); //LED
  digitalWrite(BLE_WAKE, LOW); //Sleep BLE
  
  mySerial.write("DMT"); //sleep BLE module
  mySerial.write("\r");
  delay(100);
   
  set_sleep_mode(SLEEP_MODE_PWR_DOWN); //sleep AtTiny84A
  sleep_enable();
  sleep_mode();
  
  sleep_disable(); //continues here on wake up
 
  digitalWrite(LED, HIGH); //LED
  
  //Wait for button to be released
  while(digitalRead(PCINT8) == LOW);
  while(digitalRead(PCINT9) == LOW);
  delay(500);
  wakeTime = millis();
}

ISR(PCINT1_vect)
{ 
  //set mode based on the button pressed
  if(digitalRead(PCINT9) == LOW)
  {
    mode = BUTTON_PRESS;  
  }
  else if(digitalRead(PCINT8) == LOW)
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
}

