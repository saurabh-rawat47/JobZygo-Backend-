'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { Briefcase, Search, Plus, TrendingUp, Users, Building } from 'lucide-react';
import { jobsAPI } from '@/lib/api';
import { JobPost, User } from '@/types';
import Button from '@/components/ui/Button';
import JobCard from '@/components/jobs/JobCard';

export default function Dashboard() {
  const [user, setUser] = useState<User | null>(null);
  const [recentJobs, setRecentJobs] = useState<JobPost[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }

    const fetchRecentJobs = async () => {
      try {
        const jobs = await jobsAPI.getAllJobs();
        setRecentJobs(jobs.slice(0, 6)); // Show only 6 recent jobs
      } catch (error) {
        console.error('Error fetching jobs:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchRecentJobs();
  }, []);

  const handleApplyJob = (jobId: string) => {
    // TODO: Implement job application logic
    alert('Application feature coming soon!');
  };

  const stats = [
    {
      title: 'Total Jobs',
      value: recentJobs.length,
      icon: Briefcase,
      color: 'bg-blue-500',
    },
    {
      title: 'Active Companies',
      value: new Set(recentJobs.map(job => job.companyName)).size,
      icon: Building,
      color: 'bg-green-500',
    },
    {
      title: 'Job Seekers',
      value: '500+',
      icon: Users,
      color: 'bg-purple-500',
    },
    {
      title: 'Success Rate',
      value: '95%',
      icon: TrendingUp,
      color: 'bg-orange-500',
    },
  ];

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  return (
    <div className="space-y-8">
      {/* Welcome Section */}
      <div className="bg-white rounded-lg shadow-sm p-6">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">
          Welcome back, {user?.username}!
        </h1>
        <p className="text-gray-600">
          {user?.userType === 'employer' 
            ? 'Manage your job postings and find the perfect candidates.'
            : 'Discover amazing opportunities and advance your career.'
          }
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, index) => (
          <div key={`stat-${index}`} className="bg-white rounded-lg shadow-sm p-6">
            <div className="flex items-center">
              <div className={`p-2 rounded-lg ${stat.color}`}>
                <stat.icon className="w-6 h-6 text-white" />
              </div>
              <div className="ml-4">
                <p className="text-sm font-medium text-gray-600">{stat.title}</p>
                <p className="text-2xl font-bold text-gray-900">{stat.value}</p>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Quick Actions */}
      <div className="bg-white rounded-lg shadow-sm p-6">
        <h2 className="text-xl font-semibold text-gray-900 mb-4">Quick Actions</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <Button
            onClick={() => router.push('/dashboard/jobs')}
            className="h-16 flex flex-col items-center justify-center space-y-2"
            variant="outline"
          >
            <Search className="w-6 h-6" />
            <span>Browse Jobs</span>
          </Button>
          
          {user?.userType === 'employer' && (
            <Button
              onClick={() => router.push('/dashboard/create-job')}
              className="h-16 flex flex-col items-center justify-center space-y-2"
            >
              <Plus className="w-6 h-6" />
              <span>Post New Job</span>
            </Button>
          )}
          
          <Button
            onClick={() => router.push('/dashboard/profile')}
            className="h-16 flex flex-col items-center justify-center space-y-2"
            variant="outline"
          >
            <Users className="w-6 h-6" />
            <span>View Profile</span>
          </Button>
        </div>
      </div>

      {/* Recent Jobs */}
      <div className="bg-white rounded-lg shadow-sm p-6">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-semibold text-gray-900">Recent Job Opportunities</h2>
          <Button
            onClick={() => router.push('/dashboard/jobs')}
            variant="outline"
            size="sm"
          >
            View All
          </Button>
        </div>
        
        {recentJobs.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {recentJobs.map((job, index) => (
              <JobCard
                key={job.id || `recent-job-${index}`}
                job={job}
                onApply={user?.userType === 'jobseeker' ? handleApplyJob : undefined}
              />
            ))}
          </div>
        ) : (
          <div className="text-center py-12">
            <Briefcase className="w-12 h-12 text-gray-400 mx-auto mb-4" />
            <p className="text-gray-600">No jobs available at the moment.</p>
            {user?.userType === 'employer' && (
              <Button
                onClick={() => router.push('/dashboard/create-job')}
                className="mt-4"
              >
                Post Your First Job
              </Button>
            )}
          </div>
        )}
      </div>
    </div>
  );
}


