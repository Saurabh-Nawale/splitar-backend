use splitefair

db.users.insertOne({
  username: "saurabh",
  email: "saurabh@gmail.com",
  gender: "Male"
})

db.ride_posts.insertOne({
  userId: "saurabh",
  currentLocation: "Pune",
  destination: "Mumbai",
  date: "2025-07-28",
  time: "10:30",
  interestedUsers: []
})

db.messages.insertOne({
  senderId: "saurabh",
  receiverId: "rahul",
  message: "Hey, I want to join your ride!",
  timestamp: new Date()
})

db.bookings.insertOne({
  ridePostId: "RID123",
  userId: "rahul",
  status: "confirmed",
  bookedAt: new Date()
})

db.tracks.insertOne({
  userId: "saurabh",
  location: {
    lat: 18.5204,
    lng: 73.8567
  },
  timestamp: new Date()
})
